
(ns app.schema )

(def all-apps
  ["material"
   "orderManagement"
   "procurementManagement"
   "productionManagement"
   "inventoryManagement"
   "IQC"
   "IPQC"
   "OQC"
   "QC"
   "device"
   "toolingManagement"
   "gaugeManagement"
   "ppap"
   "productTracking"
   "issueTracking"
   "measurementDataAnalysis"
   "defectsAnalysis"
   "overstockAnalysis"
   "flowControlAnalysis"
   "processEvent"
   "causeAnalysis"
   "analysis"
   "report"
   "digitalFactory"
   "IPC"
   "serialNumberManagement"])

(def router {:name nil, :title nil, :data {}, :router nil})

(def session
  {:user-id nil,
   :id nil,
   :nickname nil,
   :router (do router {:name :home, :data nil, :router nil}),
   :messages {}})

(def user {:name nil, :id nil, :nickname nil, :avatar nil, :password nil})

(def database
  {:sessions (do session {}), :users (do user {}), :enabled-apps #{}, :all-apps []})
