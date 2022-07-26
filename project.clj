(defproject chulsooboardv2 "0.1.0-SNAPSHOT"

  :description "FIXME: write description"
  :url "http://example.com/FIXME"

  :dependencies [[clojure.java-time "0.3.3"]
                 [org.postgresql/postgresql "42.4.0"]
                 [enlive "1.1.6"]
                 [cprop "0.1.19"]
                 [com.github.seancorfield/honeysql "2.2.891"]
                 [com.github.seancorfield/next.jdbc "1.2.780"]
                 [expound "0.9.0"]
                 [funcool/struct "1.4.0"]
                 [json-html "0.4.7"]
                 [luminus-immutant "0.2.5"]
                 [luminus-migrations "0.7.2"]
                 [luminus-transit "0.1.5"]
                 [markdown-clj "1.10.8"]
                 [metosin/muuntaja "0.6.8"]
                 [metosin/reitit "0.5.15"]
                 [metosin/ring-http-response "0.9.3"]
                 [mount "0.1.16"]
                 [nrepl "0.9.0"]
                 [org.clojure/clojure "1.11.1"]
                 [org.clojure/tools.cli "1.0.206"]
                 [org.clojure/tools.logging "1.2.4"]
                 [org.clojure/data.csv "1.0.1"]
                 [org.webjars.npm/bulma "0.9.3"]
                 [org.webjars.npm/material-icons "1.0.0"]
                 [org.webjars/webjars-locator "0.42"]
                 [org.webjars/webjars-locator-jboss-vfs "0.1.0"]
                 [selmer "1.12.50"]
                 ;; ============ ring things ============
                 [ring "1.9.5"]
                 [ring-webjars "0.2.0"]
                 [ring/ring-defaults "0.3.3"]
                 [ring/ring-mock "0.4.0"]
                 [ring-cors "0.1.13"]
                 [compojure "1.7.0"]
                 [hiccup "1.0.5"]
                 [garden "1.3.10"]]

  :min-lein-version "2.0.0"
  :source-paths ["src/clj"]
  :test-paths ["test/clj"]
  :resource-paths ["resources"]
  :target-path "target/%s/"
  :main ^:skip-aot chulsooboardv2.core

  :plugins [[lein-immutant "2.1.0"]
            [lein-ring "0.12.6"]]
  :ring {:handler chulsooboardv2.handler/app}

  :profiles
  {:uberjar {:omit-source true
             :aot :all
             :uberjar-name "chulsooboardv2.jar"
             :source-paths ["env/prod/clj"]
             :resource-paths ["env/prod/resources"]}

   :dev           [:project/dev :profiles/dev]
   :test          [:project/dev :project/test :profiles/test]

   :project/dev  {:jvm-opts ["-Dconf=dev-config.edn"]
                  :dependencies [[org.clojure/tools.namespace "1.2.0"]
                                 [pjstadig/humane-test-output "0.11.0"]
                                 [prone "2021-04-23"]
                                 [ring/ring-devel "1.9.5"]
                                 [ring/ring-mock "0.4.0"]]
                  :plugins      [[com.jakemccrary/lein-test-refresh "0.24.1"]
                                 [jonase/eastwood "1.2.4"]
                                 [cider/cider-nrepl "0.28.4"]]
                  :source-paths ["env/dev/clj"]
                  :resource-paths ["env/dev/resources"]
                  :repl-options {:init-ns user
                                 :timeout 120000}
                  :injections [(require 'pjstadig.humane-test-output)
                               (pjstadig.humane-test-output/activate!)]}
   :project/test {:jvm-opts ["-Dconf=test-config.edn"]
                  :resource-paths ["env/test/resources"]}
   :profiles/dev {}
   :profiles/test {}})
