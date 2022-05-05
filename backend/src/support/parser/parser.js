const apps = require('./apps');
const axios = require("axios");

const limit = 69

const monthMap = {
  "Jan": 1,
  "Feb": 2,
  "Mar": 3,
  "Apr": 4,
  "May": 5,
  "Jun": 6,
  "Jul": 7,
  "Aug": 8,
  "Sep": 9,
  "Oct": 10,
  "Nov": 11,
  "Dec": 12,
};

function convertDate(dateString) {
  const parts = dateString.split(" ");

  return "CURRENT_DATE()";
  //return `${parts[0]}-${monthMap[parts[1].replace(/.$/, '')]}-${parts[2]}`;
}

function escapeString(s) {
  return s.replace(/'/g, "''");
}


const main = async () => {
  const developers = [], publishers = [], categories = [], genres = [], articles = [], pictures = [], articleDev = [],
    articlePub = [], articleCat = [], articleGen = [], availableArticles = new Set();
  let count = 0;

  async function retrieveAndSaveDetails(appId) {
    if (count === limit) {
      console.error("Limit reached");
      return;
    }

    let details;
    try {
      count++
      details = (await axios.get(`https://store.steampowered.com/api/appdetails?appids=${appId}`)).data[appId].data;

      if (!details) return;
      if (!details.name || details.name.toLowerCase().includes("test") || details.name.toLowerCase().includes("demo")) return;

      availableArticles.add(appId)

      const type = details.type?.toUpperCase() ?? "GAME";
      if (type === "DEMO" || type === "MUSIC") return;

      const baseArticle = type === "DLC" ? details.fullgame.appid : "NULL";
      if (baseArticle !== "NULL" && !availableArticles.has(baseArticle)) {
        console.error(`${count}/${limit}`, `Base article ${baseArticle} not available`);
        return;
      }

      let addedSet = new Set()
      if (details.developers) {
        for (let x of details.developers) {
          if (developers.includes(x)) continue;
          developers.push(x);
        }
        for (let x of details.developers) {
          if (addedSet.has(x)) continue;
          articleDev.push([appId, developers.indexOf(x)]);
          addedSet.add(x);
        }
      }
      addedSet.clear()

      if (details.publishers) {
        for (let x of details.publishers) {
          if (publishers.includes(x)) continue;
          publishers.push(x);
        }
        for (let x of details.publishers) {
          if (addedSet.has(x)) continue;
          articlePub.push([appId, publishers.indexOf(x)]);
          addedSet.add(x);
        }
      }
      addedSet.clear()

      if (details.categories) {
        for (let x of details.categories) {
          if (categories.includes(x.description)) continue;
          categories.push(x.description);
        }
        for (let x of details.categories) {
          if (addedSet.has(x.description)) continue;
          articleCat.push([appId, categories.indexOf(x.description)]);
          addedSet.add(x.description);
        }
      }
      addedSet.clear()

      if (details.genres) {
        for (let x of details.genres) {
          if (genres.includes(x.description)) continue;
          genres.push(x.description);
        }
        let addedSet = new Set()
        for (let x of details.genres) {
          if (addedSet.has(x.description)) continue;
          articleGen.push([appId, genres.indexOf(x.description)]);
          addedSet.add(x.description);
        }
      }

      articles.push(`INSERT INTO Article (id, title, description, price, currency, picture_url, release_date, article_type, id_base_article) VALUES (${appId}, '${escapeString(details.name)}', '${escapeString(details.detailed_description)}', ${(details.price_overview?.initial ?? 0) / 100}, '${details.price_overview?.currency ?? "EUR"}', '${details.header_image}', ${convertDate(details.release_date.date)}, '${type}', ${baseArticle});`);

      if (details.screenshots) {
        for (x of details.screenshots) {
          pictures.push({id: pictureId++, full: x.path_full, thumb: x.path_thumbnail, appid: appId});
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
          await retrieveAndSaveDetails(x)
          dlcCount++;
        }
      }
      console.error(`${count}/${limit}`, `${appId} - ${details.name} ready to include! (${type})`);
    } catch (error) {
      console.error(error, appId)
    }
  }

  let pictureId = 1;
  for (let app of apps) {
    try {
      await retrieveAndSaveDetails(app)

      if (count === limit) break;
    } catch (error) {
      console.error(error.message)
      return
    }
  }

  try {
    console.log("-- DEVELOPERS");
    developers.forEach((name, i) => console.log(`INSERT INTO developer (id, name) VALUES (${i}, '${escapeString(name)}');`));

    console.log("\n-- PUBLISHERS");
    publishers.forEach((name, i) => console.log(`INSERT INTO publisher (id, name) VALUES (${i}, '${escapeString(name)}');`));

    console.log("\n-- CATEGORIES");
    categories.forEach((name, i) => console.log(`INSERT INTO category (id, name) VALUES (${i}, '${escapeString(name)}');`));

    console.log("\n-- GENRES");
    genres.forEach((name, i) => console.log(`INSERT INTO genre (id, name) VALUES (${i}, '${escapeString(name)}');`));

    console.log("\n-- ARTICLES")
    articles.forEach(x => console.log(x));

    console.log("\n-- ARTICLE DEVELOPERS")
    articleDev.forEach(x => console.log(`INSERT INTO article_developer (id_article, id_developer) VALUES (${x[0]}, ${x[1]});`));

    console.log("\n-- ARTICLE PUBLISHERS")
    articlePub.forEach(x => console.log(`INSERT INTO article_publisher (id_article, id_publisher) VALUES (${x[0]}, ${x[1]});`));

    console.log("\n-- ARTICLE CATEGORIES")
    articleCat.forEach(x => console.log(`INSERT INTO article_category (id_article, id_category) VALUES (${x[0]}, ${x[1]});`));

    console.log("\n-- ARTICLE GENRES")
    articleGen.forEach(x => console.log(`INSERT INTO article_genre (id_article, id_genre) VALUES (${x[0]}, ${x[1]});`));

    console.log("\n-- PICTURES")
    pictures.forEach(x => console.log(`INSERT INTO Picture (id, url_full, url_thumbnail, id_article) VALUES (${x.id}, '${x.full}', '${x.thumb}', ${x.appid});`));
  } catch (error) {
    console.error(error)
  }
}

main();
