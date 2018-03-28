(ns gdsebffk.server
	(:require [gdsebffk.handler :refer [app]]
						[config.core :refer [env]]
						[ring.adapter.jetty :refer [run-jetty]])
	(:gen-class))

(defn start [port]
	(run-jetty app {:port  port
									:join? false}))

(defn -main []
	(let [port (Integer. (or (System/getenv "PORT") "8080"))]
		(start port)))
