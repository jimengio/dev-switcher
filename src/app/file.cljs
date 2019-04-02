
(ns app.file
  (:require [clojure.string :as string] [app.schema :as schema] ["fs" :as fs])
  (:require-macros [clojure.core.strint :refer [<<]]))

(defn write-ts-file! [enabled-apps all-apps]
  (println "files to write:" enabled-apps)
  (let [apps-in-order (map :id all-apps)
        imports-map (->> all-apps (map (fn [x] [(:id x) (:import x)])) (into {}))
        import-lines (->> enabled-apps
                          (sort-by (fn [app] (.indexOf apps-in-order app)))
                          (map (fn [app] (get imports-map app)))
                          (string/join "\n"))
        import-names (->> enabled-apps
                          (sort-by (fn [app] (.indexOf apps-in-order app)))
                          (map (fn [app] (str app "Config,")))
                          (string/join "\n"))]
    (fs/writeFileSync
     "dev-apps.ts"
     (<<
      "~{import-lines}\n\nconst apps = [\n~{import-names}\n];\n\nexport function getAppsConfig() {\n  return apps;\n}\n\nexport function getAppRouteConfigs() {\n  return apps.map((app) => app.module.routeConfig[0]);\n}\n"))))
