
(ns app.comp.workspace
  (:require [hsl.core :refer [hsl]]
            [respo-ui.core :as ui]
            [respo.comp.space :refer [=<]]
            [respo.core :refer [defcomp <> action-> list-> span div button]]
            [app.config :as config]
            [clojure.set :refer [difference]]
            [app.schema :as schema])
  (:require-macros [clojure.core.strint :refer [<<]]))

(defcomp
 comp-app
 (app-name title op)
 (div
  {:style {:display :inline-block,
           :width 160,
           :border (<< "1px solid ~(hsl 200 80 86)"),
           :background-color (hsl 0 0 97),
           :padding "4px 8px",
           :margin 4,
           :cursor :pointer},
   :on-click (fn [e d! m!] (d! op app-name))}
  (div {} (<> title))
  (div
   {:style {:color (hsl 0 0 70), :font-family ui/font-fancy, :font-size 12}}
   (<> app-name))))

(defcomp
 comp-workspace
 (enabled-apps all-apps)
 (let [apps-order (map :id all-apps)
       titles-map (->> all-apps (map (fn [x] [(:id x) (:name x)])) (into {}))]
   (div
    {:style (merge ui/flex ui/row {:padding 8, :overflow :auto})}
    (div
     {:style (merge ui/flex {:overflow :auto})}
     (div
      {}
      (<> "Enabled")
      (=< 16 nil)
      (button
       {:style ui/button,
        :inner-text "Enable all",
        :on-click (fn [e d! m!] (d! :app/turn-on-all))}))
     (=< nil 16)
     (list->
      {:style {}}
      (->> enabled-apps
           (sort-by (fn [app-name] (.indexOf apps-order app-name)))
           (map
            (fn [app-name]
              [app-name (comp-app app-name (get titles-map app-name) :app/turn-off)])))))
    (=< 24 nil)
    (div
     {:style (merge {:overflow :auto, :flex 2})}
     (div
      {:style {:overflow :auto}}
      (<> "Disabled")
      (=< 16 nil)
      (button
       {:style ui/button,
        :inner-text "Disable all",
        :on-click (fn [e d! m!] (d! :app/turn-off-all))}))
     (=< nil 16)
     (list->
      {:style {:overflow :auto}}
      (->> (difference (set apps-order) enabled-apps)
           (sort-by (fn [app-name] (.indexOf apps-order app-name)))
           (map
            (fn [app-name]
              [app-name (comp-app app-name (get titles-map app-name) :app/turn-on)]))))))))
