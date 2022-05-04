const axios = require("axios");

const limit = 200

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
  let count = -1;

  async function retrieveAndSaveDetails(appId) {
    count++
    let details;

    try {
      details = (await axios.get(`https://store.steampowered.com/api/appdetails?appids=${appId}`)).data[appId].data;

      availableArticles.add(appId)

      const type = details.type.toUpperCase();
      const baseArticle = type === "dlc" ? details.fullgame.appid : "NULL";

      if (details.type === "demo") return;
      if (!details.name || details.name.toLowerCase().includes("test") || details.name.toLowerCase().includes("demo")) return;
      if (details.type === "dlc" && !availableArticles.has(baseArticle)) {
        if (count + 1 !== limit) return;
        await retrieveAndSaveDetails(baseArticle)
      }

      for (let x of details.developers) {
        if (developers.includes(x)) continue;
        developers.push(x);
      }
      for (let x of details.developers) {
        articleDev.push([appId, developers.indexOf(x)]);
      }
      for (let x of details.publishers) {
        if (publishers.includes(x)) continue;
        publishers.push(x);
      }
      for (let x of details.publishers) {
        articlePub.push([appId, publishers.indexOf(x)]);
      }
      for (let x of details.categories) {
        if (categories.includes(x.description)) continue;
        categories.push(x.description);
      }
      for (let x of details.categories) {
        articleCat.push([appId, categories.indexOf(x.description)]);
      }
      for (let x of details.genres) {
        if (genres.includes(x.description)) continue;
        genres.push(x.description);
      }
      for (let x of details.genres) {
        articleGen.push([appId, genres.indexOf(x.description)]);
      }

      articles.push(`INSERT INTO Article (id, title, description, price, currency, picture_url, release_date, article_type, id_base_article) VALUES (${appId}, '${escapeString(details.name)}', '${escapeString(details.detailed_description)}', ${details.price_overview.initial / 100}, '${details.price_overview.currency}', '${details.header_image}', ${convertDate(details.release_date.date)}, '${type}', ${baseArticle});`);

      for (x of details.screenshots) {
        pictures.push({id: pictureId++, full: x.path_full, thumb: x.path_thumbnail, appid: appId});
      }
    } catch (error) {
      console.error(error, details)
    }
  }

  let pictureId = 1;

  const apps = (await axios.get("http://api.steampowered.com/ISteamApps/GetAppList/v0002/?format=json")).data.applist.apps;

  for (let app of apps) {
    try {
      if(Math.random() > 0.5) continue;
      if (!app.name || app.name.toLowerCase().includes("test") || app.name.toLowerCase().includes("demo")) continue;

      await retrieveAndSaveDetails(app.appid)

      if (count === limit) break;
    } catch (error) {
      console.error(error.message)
      return
    }
  }

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
}

main();
