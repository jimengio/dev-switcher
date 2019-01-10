
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
 (app-name op)
 (div
  {:style {:display :inline-block,
           :width 200,
           :border (<< "1px solid ~(hsl 200 80 86)"),
           :background-color (hsl 0 0 97),
           :padding "8px 16px",
           :margin 8,
           :cursor :pointer},
   :on-click (fn [e d! m!] (d! op app-name))}
  (div {} (<> (get schema/app-names app-name app-name)))
  (div {:style {:color (hsl 0 0 70), :font-family ui/font-fancy}} (<> app-name))))

(defcomp
 comp-workspace
 (enabled-apps)
 (div
  {:style (merge ui/flex ui/row {:padding 16, :overflow :auto})}
  (div
   {:style ui/flex}
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
         (sort-by (fn [app-name] (.indexOf schema/all-apps app-name)))
         (map (fn [app-name] [app-name (comp-app app-name :app/turn-off)])))))
  (=< 24 nil)
  (div
   {:style ui/flex}
   (div
    {}
    (<> "Disabled")
    (=< 16 nil)
    (button
     {:style ui/button,
      :inner-text "Disable all",
      :on-click (fn [e d! m!] (d! :app/turn-off-all))}))
   (=< nil 16)
   (list->
    {}
    (->> (difference (set schema/all-apps) enabled-apps)
         (sort-by (fn [app-name] (.indexOf schema/all-apps app-name)))
         (map (fn [app-name] [app-name (comp-app app-name :app/turn-on)])))))))
