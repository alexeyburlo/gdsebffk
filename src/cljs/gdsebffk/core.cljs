(ns gdsebffk.core
	(:require [reagent.core :as reagent :refer [atom]]
						[secretary.core :as secretary :include-macros true]
						[accountant.core :as accountant]))

;; -------------------------
;; Views

(defonce app-state (atom {:text "FACEBOOK" :data "ГДҪЭБФФҚ" :caption "It's secure and always will be. Stay tuned."}))

(def github {:href "https://github.com/artl/gdsebffk"})

(defn github-badge []
	[:a.github-badge
	 github
	 [:img {:style {:position "absolute" :top 0 :left 0 :border 0}
					:alt   "Fork me on GitHub"
					:src   "https://s3.amazonaws.com/github/ribbons/forkme_left_red_aa0000.png"}]])

(defn home-page []
	[:div.information
	 [:div.snafu {:data-text (:data @app-state)} (:text @app-state)]
	 [:div.caption (:caption @app-state)]
	 (github-badge)])

;; -------------------------
;; Routes

(defonce page (atom #'home-page))

(defn current-page []
	[:div [@page]])

(secretary/defroute "/" []
										(reset! page #'home-page))

;; -------------------------
;; Initialize app

(defn mount-root []
	(reagent/render [current-page] (.getElementById js/document "app")))

(defn init! []
	(accountant/configure-navigation!
		{:nav-handler
		 (fn [path]
			 (secretary/dispatch! path))
		 :path-exists?
		 (fn [path]
			 (secretary/locate-route path))})
	(accountant/dispatch-current!)
	(mount-root))
