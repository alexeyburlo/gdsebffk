(ns ^:figwheel-no-load gdsebffk.dev
  (:require
    [gdsebffk.core :as core]
    [devtools.core :as devtools]))

(devtools/install!)

(enable-console-print!)

(core/init!)
