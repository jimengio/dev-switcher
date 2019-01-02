
(ns app.config (:require-macros [clojure.core.strint :refer [<<]]))

(def cdn?
  (cond
    (exists? js/window) false
    (exists? js/process) (= "true" js/process.env.cdn)
    :else false))

(def dev?
  (let [debug? (do ^boolean js/goog.DEBUG)]
    (if debug?
      (cond
        (exists? js/window) true
        (exists? js/process) (not= "true" js/process.env.release)
        :else true)
      false)))

(defn get-cdn-info [cdn-key]
  {:cdn-url (<< "http://cdn.tiye.me/~{cdn-key}/"), :cdn-folder "tiye.me:cdn/~{cdn-key}"})

(def shared-info
  {:release-ui "http://cdn.tiye.me/favored-fonts/main.css",
   :dev-ui "http://localhost:8100/main.css"})

(def site
  (merge
   shared-info
   (get-cdn-info "dev-apps-switcher")
   {:port 8010,
    :title "DevApps",
    :icon "http://cdn.tiye.me/logo/jimeng-360x360.png",
    :upload-folder "tiye.me:repo/jimengio/dev-apps-switcher/",
    :server-folder nil,
    :theme "#eeeeff",
    :storage-key "dev-apps-switcher",
    :storage-file "dev-apps.edn"}))
