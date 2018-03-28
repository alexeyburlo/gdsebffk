(ns gdsebffk.handler
	(:require [compojure.core :refer [GET defroutes]]
						[compojure.route :refer [not-found resources]]
						[hiccup.page :refer [include-js include-css html5]]
						[gdsebffk.middleware :refer [wrap-middleware]]
						[config.core :refer [env]]))

(def mount-target
	[:div#app
	 [:h3 "ClojureScript has not been compiled!"]])

(defn head []
	[:head
	 [:meta {:charset "utf-8"}]
	 [:meta {:name    "viewport"
					 :content "width=device-width, initial-scale=1"}]
	 [:meta {:name "title" :content "ГДСЭБФФК"}]
	 [:meta {:name "description" :content "Project inspired by Silicon Valley season 5 opening credits"}]
	 [:title "ГДСЭБФФК"]
	 (include-css (if (env :dev) "/css/site.css" "/css/site.min.css"))
	 [:link {:rel "icon" :href "/images/favicon.ico"}]])

(defn loading-page []
	(html5
		(head)
		[:body {:class "body-container"}
		 mount-target
		 (include-js "/js/app.js")]))


(defroutes routes
					 (GET "/" [] (loading-page))

					 (resources "/")
					 (not-found "Not Found"))

(def app (wrap-middleware #'routes))
