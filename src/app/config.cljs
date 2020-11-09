
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
  {:cdn-url (<< "https://fe.jimu.io/~{cdn-key}/"),
   :cdn-folder (<< "fe.jimu.io:cdn/~{cdn-key}")})

(def shared-info
  {:release-ui "https://fe.jimu.io/favored-fonts/main.css",
   :dev-ui "http://localhost:8100/main.css"})

(def site
  (merge
   shared-info
   (get-cdn-info "dev-switcher")
   {:port 8011,
    :ws-host "localhost",
    :title "Dev Switcher",
    :icon "https://fe.jimu.io/logo/dev-switcher.png",
    :upload-folder "fe.jimu.io:repo/jimengio/dev-switcher/",
    :server-folder nil,
    :theme "#eeeeff",
    :storage-key "dev-switcher",
    :storage-file "dev-apps.edn"}))
