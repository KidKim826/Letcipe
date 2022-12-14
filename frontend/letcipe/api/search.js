import interceptor from '../config/interceptor'

const api = interceptor

async function getRecipes(object, success, fail) {
  await api.get(`/api/recipe`, { params: object }).then(success).catch(fail)
}
async function getHotRecipes(object, success, fail) {
  await api.get(`/api/recipe/hot`, { params: object }).then(success).catch(fail)
}
async function getChartInfo(object, success, fail) {
  await api
    .get(`/api/admin/recipe`, { params: object })
    .then(success)
    .catch(fail)
}
async function getRecipesIngre(object, success, fail) {
  await api.get(`/api/recipe`, { params: object }).then(success).catch(fail)
}
async function getRecipeList(keyword, size, page, success, fail) {
  await api
    .get(`/api/recipelist?keyword=${keyword}&page=${page}&size=${size}`)
    .then(success)
    .catch(fail)
}

async function getTotalNumRecipe(object, success, fail){
    await api
    .get(`/api/recipe/totalNum`, {params : object})
    .then(success)
    .catch(fail)

}
async function getTotalNumRecipeList(object, success, fail){
    await api
    .get(`/api/recipelist/totalNum`, {params : object})
    .then(success)
    .catch(fail)

}
export {
    getRecipes,
    getRecipesIngre,
    getRecipeList,
    getTotalNumRecipe,
    getTotalNumRecipeList,
    getHotRecipes,
    getChartInfo,
}
