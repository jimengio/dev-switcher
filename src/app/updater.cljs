
(ns app.updater
  (:require [app.updater.session :as session]
            [app.updater.user :as user]
            [app.updater.router :as router]
            [app.schema :as schema]
            [respo-message.updater :refer [update-messages]]))

(defn app-turn-off [db op-data sid op-id op-time]
  (update db :enabled-apps (fn [apps] (disj apps op-data))))

(defn app-turn-off-all [db op-data sid op-id op-time] (assoc db :enabled-apps #{}))

(defn app-turn-on [db op-data sid op-id op-time]
  (update db :enabled-apps (fn [apps] (conj apps op-data))))

(defn app-turn-on-all [db op-data sid op-id op-time]
  (assoc db :enabled-apps (set schema/all-apps)))

(defn updater [db op op-data sid op-id op-time]
  (let [f (case op
            :session/connect session/connect
            :session/disconnect session/disconnect
            :session/remove-message session/remove-message
            :user/log-in user/log-in
            :user/sign-up user/sign-up
            :user/log-out user/log-out
            :router/change router/change
            :app/turn-on app-turn-on
            :app/turn-off app-turn-off
            :app/turn-off-all app-turn-off-all
            :app/turn-on-all app-turn-on-all
            (do (println "Unknown op:" op) identity))]
    (f db op-data sid op-id op-time)))
