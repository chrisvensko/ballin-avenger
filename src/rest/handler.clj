(ns rest.handler
  (:use compojure.core)
  (:use rest.file)
  (:require [compojure.handler :as handler]
    [ring.middleware.json :as middleware]
    [ring.util.response :as resp]
    [compojure.route :as route]))

(defroutes api-routes
  (context "/file" [] file-routes))

(defroutes app-routes
  (GET "/" [] (resp/resource-response "index.html" {:root "public"}))
  (context "/api/v1" [] api-routes)
  (route/resources "/")
  (route/not-found "Route Not Found"))

(def app
  (-> (handler/api app-routes)
    (middleware/wrap-json-body)
    (middleware/wrap-json-params)
    (middleware/wrap-json-response)))