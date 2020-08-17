
(ns app.comp.workspace
  (:require [hsl.core :refer [hsl]]
            [respo-ui.core :as ui]
            [respo.comp.space :refer [=<]]
            [respo.core :refer [defcomp <> list-> span div button]]
            [app.config :as config]
            [clojure.set :refer [difference]]
            [app.schema :as schema])
  (:require-macros [clojure.core.strint :refer [<<]]))

(defcomp
 comp-app
 (app-name title enabled?)
 (div
  {:style (merge
           {:display :inline-block,
            :width 160,
            :border (<< "2px solid ~(hsl 200 80 90)"),
            :background-color (hsl 0 0 97),
            :padding "8px 8px",
            :margin 4,
            :cursor :pointer,
            :font-size 16,
            :line-height "24px",
            :opacity 0.5}
           (if enabled? {:opacity 1, :border (<< "2px solid ~(hsl 200 80 66)")})),
   :on-click (fn [e d!] (d! (if enabled? :app/turn-off :app/turn-on) app-name))}
  (div {} (<> title))
  (div
   {:style {:color (hsl 0 0 80), :font-family ui/font-fancy, :font-size 14}}
   (<> app-name))))

(defcomp
 comp-workspace
 (enabled-apps all-apps)
 (let [apps-order (map :id all-apps)
       titles-map (->> all-apps (map (fn [x] [(:id x) (:name x)])) (into {}))]
   (div
    {:style (merge ui/flex ui/row {:padding 16, :overflow :auto})}
    (div
     {:style (merge ui/flex {:overflow :auto})}
     (div
      {}
      (button
       {:style ui/button,
        :inner-text "Enable all",
        :on-click (fn [e d!] (d! :app/turn-on-all))})
      (=< 8 nil)
      (button
       {:style ui/button,
        :inner-text "Disable all",
        :on-click (fn [e d!] (d! :app/turn-off-all))})
      (=< 16 nil)
      (<>
       (str (count enabled-apps) " apps enabled")
       {:font-family ui/font-fancy, :font-size 16}))
     (=< nil 16)
     (list->
      {:style {}}
      (->> all-apps
           (map :id)
           (sort-by (fn [app-name] (.indexOf apps-order app-name)))
           (map
            (fn [app-name]
              [app-name
               (comp-app
                app-name
                (get titles-map app-name)
                (contains? enabled-apps app-name))]))))))))
