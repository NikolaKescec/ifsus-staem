const apps = require("./apps");
const axios = require("axios");

const limit = apps.length;
const timeout = 90000;

const monthMap = {
  Jan: 1,
  Feb: 2,
  Mar: 3,
  Apr: 4,
  May: 5,
  Jun: 6,
  Jul: 7,
  Aug: 8,
  Sep: 9,
  Oct: 10,
  Nov: 11,
  Dec: 12,
};

function convertDate(dateString) {
  const parts = dateString.split(" ");

  const day = parseInt(parts[0]);
  const month = parseInt(monthMap[parts[1]?.replace(/.$/, "")]);
  const year = parseInt(parts[2]);

  if (isNaN(month) || isNaN(day) || isNaN(year)) {
    return "CURRENT_DATE";
  }

  return `'${year}-${month}-${day}'`;
}

function escapeString(s) {
  return s.replace(/'/g, "''");
}

function getIndex(array, item) {
  return array.indexOf(item) + 1;
}

const main = async () => {
  const developers = [],
    publishers = [],
    categories = [],
    genres = [],
    articles = [],
    pictures = [],
    articleDev = [],
    articlePub = [],
    articleCat = [],
    articleGen = [],
    availableArticles = [];
  let count = 0;

  async function retrieveAndSaveDetails(appId) {
    if (count === limit) {
      console.error("Limit reached");
      return;
    }

    let details;
    try {
      if (count !== 0 && count % 60 === 0) {
        console.error(
          `${count} divisible by 60, waiting for ${timeout / 1000} seconds!`
        );
        await new Promise((resolve) => setTimeout(resolve, timeout));
      }
      count++;
      details = (
        await axios.get(
          `https://store.steampowered.com/api/appdetails?appids=${appId}`
        )
      ).data[appId].data;

      if (!details) return;
      if (
        !details.name ||
        details.name.toLowerCase().includes("test") ||
        details.name.toLowerCase().includes("demo")
      )
        return;

      if (availableArticles.includes(appId)) {
        console.error(`${appId} already exists`);
        return;
      }

      const type = details.type?.toUpperCase() ?? "GAME";
      if (type === "DEMO" || type === "MUSIC") return;

      const baseArticle =
        type === "DLC"
          ? getIndex(availableArticles, details.fullgame.appid)
          : "NULL";
      if (baseArticle !== "NULL" && baseArticle === 0) {
        console.error(
          `${count}/${limit}`,
          `Base article ${baseArticle} not available`
        );
        return;
      }
      availableArticles.push(appId);

      let addedSet = new Set();
      if (details.developers) {
        for (let x of details.developers) {
          if (developers.includes(x)) continue;
          developers.push(x);
        }
        for (let x of details.developers) {
          if (addedSet.has(x)) continue;
          articleDev.push([
            getIndex(availableArticles, appId),
            getIndex(developers, x),
          ]);
          addedSet.add(x);
        }
      }
      addedSet.clear();

      if (details.publishers) {
        for (let x of details.publishers) {
          if (publishers.includes(x)) continue;
          publishers.push(x);
        }
        for (let x of details.publishers) {
          if (addedSet.has(x)) continue;
          articlePub.push([
            getIndex(availableArticles, appId),
            getIndex(publishers, x),
          ]);
          addedSet.add(x);
        }
      }
      addedSet.clear();

      if (details.categories) {
        for (let x of details.categories) {
          if (categories.includes(x.description)) continue;
          categories.push(x.description);
        }
        for (let x of details.categories) {
          if (addedSet.has(x.description)) continue;
          articleCat.push([
            getIndex(availableArticles, appId),
            getIndex(categories, x.description),
          ]);
          addedSet.add(x.description);
        }
      }
      addedSet.clear();

      if (details.genres) {
        for (let x of details.genres) {
          if (genres.includes(x.description)) continue;
          genres.push(x.description);
        }
        let addedSet = new Set();
        for (let x of details.genres) {
          if (addedSet.has(x.description)) continue;
          articleGen.push([
            getIndex(availableArticles, appId),
            getIndex(genres, x.description),
          ]);
          addedSet.add(x.description);
        }
      }

      articles.push(
        `INSERT INTO Article (title, description, price, currency, picture_url, release_date, article_type, id_base_article) VALUES ('${escapeString(
          details.name
        )}', '${escapeString(details.detailed_description)}', ${
          (details.price_overview?.initial ?? 0) / 100
        }, '${details.price_overview?.currency ?? "EUR"}', '${
          details.header_image
        }', ${convertDate(
          details.release_date.date
        )}, '${type}', ${baseArticle});`
      );

      if (details.screenshots) {
        for (x of details.screenshots) {
          pictures.push({
            full: x.path_full,
            thumb: x.path_thumbnail,
            appid: getIndex(availableArticles, appId),
          });
        }
      }

      if (details.dlc) {
        let dlcCount = 0;
        for (x of details.dlc) {
          if (dlcCount === 4) {
            console.error("DLC limit reached");
            break;
          }
          console.error(`Fetching DLC ${x} for ${details.name}`);
          await retrieveAndSaveDetails(x);
          dlcCount++;
        }
      }
      console.error(
        `${count}/${limit}`,
        `appId = ${appId}|order = ${getIndex(availableArticles, appId)} - ${
          details.name
        } ready to include! (${
          type === "DLC" ? "DLC, base game " + baseArticle : type
        })`
      );
    } catch (error) {
      console.error(error, appId);
    }
  }

  for (let app of apps) {
    try {
      await retrieveAndSaveDetails(app);

      if (count === limit) break;
    } catch (error) {
      console.error(error.message);
      return;
    }
  }

  try {
    console.log("-- DEVELOPERS");
    developers.forEach((name) =>
      console.log(
        `INSERT INTO developer (name) VALUES ('${escapeString(name)}');`
      )
    );

    console.log("\n-- PUBLISHERS");
    publishers.forEach((name) =>
      console.log(
        `INSERT INTO publisher (name) VALUES ('${escapeString(name)}');`
      )
    );

    console.log("\n-- CATEGORIES");
    categories.forEach((name) =>
      console.log(
        `INSERT INTO category (name) VALUES ('${escapeString(name)}');`
      )
    );

    console.log("\n-- GENRES");
    genres.forEach((name) =>
      console.log(`INSERT INTO genre (name) VALUES ('${escapeString(name)}');`)
    );

    console.log("\n-- ARTICLES");
    articles.forEach((x) => console.log(x));

    console.log("\n-- ARTICLE DEVELOPERS");
    articleDev.forEach((x) =>
      console.log(
        `INSERT INTO article_developer (id_article, id_developer) VALUES (${x[0]}, ${x[1]});`
      )
    );

    console.log("\n-- ARTICLE PUBLISHERS");
    articlePub.forEach((x) =>
      console.log(
        `INSERT INTO article_publisher (id_article, id_publisher) VALUES (${x[0]}, ${x[1]});`
      )
    );

    console.log("\n-- ARTICLE CATEGORIES");
    articleCat.forEach((x) =>
      console.log(
        `INSERT INTO article_category (id_article, id_category) VALUES (${x[0]}, ${x[1]});`
      )
    );

    console.log("\n-- ARTICLE GENRES");
    articleGen.forEach((x) =>
      console.log(
        `INSERT INTO article_genre (id_article, id_genre) VALUES (${x[0]}, ${x[1]});`
      )
    );

    console.log("\n-- PICTURES");
    pictures.forEach((x) =>
      console.log(
        `INSERT INTO Picture (url_full, url_thumbnail, id_article) VALUES ('${x.full}', '${x.thumb}', ${x.appid});`
      )
    );
  } catch (error) {
    console.error(error);
  }
};

main();
