
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
   "IPC"])

(def app-imports
  {"analysis" "import analysisConfig from \"./analysis/config\";",
   "causeAnalysis" "import causeAnalysisConfig from \"./causeAnalysis/config\";",
   "defectsAnalysis" "import defectsAnalysisConfig from \"./defectsAnalysis/config\";",
   "device" "import deviceConfig from \"./device/config\";",
   "digitalFactory" "import digitalFactoryConfig from \"./digitalFactory/config\";",
   "flowControlAnalysis" "import flowControlAnalysisConfig from \"./flowControlAnalysis/config\";",
   "gaugeManagement" "import gaugeManagementConfig from \"./gaugeManagement/config\";",
   "inventoryManagement" "import inventoryManagementConfig from \"./inventoryManagement/config\";",
   "IPQC" "import IPQCConfig from \"./IPQC/config\";",
   "IQC" "import IQCConfig from \"./IQC/config\";",
   "QC" "import QCConfig from \"./QC/config\";",
   "issueTracking" "import issueTrackingConfig from \"./issueTracking/config\";",
   "material" "import materialConfig from \"./material/config\";",
   "measurementDataAnalysis" "import measurementDataAnalysisConfig from \"./measurementDataAnalysis/config\";",
   "OQC" "import OQCConfig from \"./OQC/config\";",
   "orderManagement" "import orderManagementConfig from \"./orderManagement/config\";",
   "overstockAnalysis" "import overstockAnalysisConfig from \"./overstockAnalysis/config\";",
   "ppap" "import ppapConfig from \"./ppap/config\";",
   "processEvent" "import processEventConfig from \"./processEvent/config\";",
   "procurementManagement" "import procurementManagementConfig from \"./procurementManagement/config\";",
   "productionManagement" "import productionManagementConfig from \"./productionManagement/config\";",
   "productTracking" "import productTrackingConfig from \"./productTracking/config\";",
   "report" "import reportConfig from \"./report/config\";",
   "toolingManagement" "import toolingManagementConfig from \"./toolingManagement/config\";",
   "IPC" "import IPCConfig from \"./IPC/config\";"})

(def app-names
  {"material" "产品信息管理",
   "orderManagement" "销售管理",
   "procurementManagement" "采购管理",
   "productionManagement" "生产管理",
   "inventoryManagement" "库存管理",
   "IQC" "来料检测",
   "IPQC" "生产过程检测",
   "OQC" "出货检测",
   "device" "设备管理",
   "toolingManagement" "工装管理",
   "gaugeManagement" "计量仪器管理",
   "ppap" "PPAP",
   "productTracking" "产品追溯",
   "issueTracking" "质量问题跟踪",
   "measurementDataAnalysis" "测量数据分析",
   "defectsAnalysis" "缺陷统计",
   "overstockAnalysis" "呆料分析",
   "flowControlAnalysis" "流控分析",
   "processEvent" "工艺事件",
   "causeAnalysis" "问题原因分析",
   "analysis" "数据分析",
   "report" "智能报告",
   "digitalFactory" "配置中心",
   "QC" "质量管理",
   "IPC" "工控机(IPC)"})

(def router {:name nil, :title nil, :data {}, :router nil})

(def session
  {:user-id nil,
   :id nil,
   :nickname nil,
   :router (do router {:name :home, :data nil, :router nil}),
   :messages {}})

(def user {:name nil, :id nil, :nickname nil, :avatar nil, :password nil})

(def database {:sessions (do session {}), :users (do user {}), :enabled-apps #{}})
