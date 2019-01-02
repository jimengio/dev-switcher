
(ns app.comp.workspace
  (:require [hsl.core :refer [hsl]]
            [respo-ui.core :as ui]
            [respo.comp.space :refer [=<]]
            [respo.core :refer [defcomp <> action-> list-> span div button]]
            [app.config :as config]
            [clojure.set :refer [difference]]
            [app.schema :as schema]))

(defcomp
 comp-app
 (app-name op)
 (div
  {:style {:display :inline-block,
           :width 240,
           :border "1px solid #aaf",
           :padding "8px 16px",
           :margin 8,
           :cursor :pointer},
   :on-click (fn [e d! m!] (d! op app-name))}
  (<> (get schema/app-names app-name app-name))))

(defcomp
 comp-workspace
 (enabled-apps)
 (div
  {:style {:padding 16}}
  (div
   {}
   (<> "Workspace")
   (=< 16 nil)
   (button
    {:style ui/button, :inner-text "Save", :on-click (fn [e d! m!] (d! :effect/save nil))}))
  (=< nil 24)
  (div {} (<> "Enabled"))
  (list->
   {:style {:min-height 120}}
   (->> enabled-apps (map (fn [app-name] [app-name (comp-app app-name :app/turn-off)]))))
  (div {} (<> "Disabled"))
  (list->
   {}
   (->> (difference (set schema/all-apps) enabled-apps)
        (map (fn [app-name] [app-name (comp-app app-name :app/turn-on)]))))))
