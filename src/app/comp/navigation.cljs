
(ns app.comp.navigation
  (:require [hsl.core :refer [hsl]]
            [respo-ui.core :as ui]
            [respo.comp.space :refer [=<]]
            [respo.core :refer [defcomp <> action-> button span div]]
            [app.config :as config]))

(defcomp
 comp-navigation
 (need-save?)
 (div
  {:style (merge
           ui/row-center
           {:height 48,
            :justify-content :space-between,
            :padding "0 8px",
            :font-size 16,
            :border-bottom (str "1px solid " (hsl 0 0 0 0.1)),
            :font-family ui/font-fancy})}
  (div
   {:on-click (action-> :router/change {:name :home}), :style {:cursor :pointer}}
   (<> (:title config/site) nil))
  (button
   {:style (merge
            ui/button
            (if need-save?
              {:background-color (hsl 200 80 50),
               :color :white,
               :border-color (hsl 200 80 50)})),
    :inner-text "Save",
    :on-click (fn [e d! m!] (d! :effect/save nil))})))
