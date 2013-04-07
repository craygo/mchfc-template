(ns leiningen.new.mchfc
  (:use [leiningen.new.templates :only [renderer name-to-path ->files]]))

(def render (renderer "mchfc"))

(defn mchfc
  "FIXME: write documentation"
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (->files data
             [".gitignore"  (render "gitignore" data)]
             ["project.clj" (render "project.clj" data)]
             ["README.md"   (render "README.md" data)]
             ["config.rb"   (render "config.rb")]
             ["src/{{sanitized}}/core.clj" (render "src/mchfc/core.clj" data)]
             ["src/{{sanitized}}/controller.clj" (render "src/mchfc/controller.clj" data)]
             ["src/{{sanitized}}/dal.clj" (render "src/mchfc/dal.clj" data)]
             ["src/{{sanitized}}/friend.clj" (render "src/mchfc/friend.clj" data)]
             ["src/{{sanitized}}/management.clj" (render "src/mchfc/management.clj" data)]
             ["src/{{sanitized}}/mgmt_view.clj" (render "src/mchfc/mgmt_view.clj" data)]
             ["src/{{sanitized}}/model.clj" (render "src/mchfc/model.clj" data)]
             ["src/{{sanitized}}/setup.clj" (render "src/mchfc/setup.clj" data)]
             ["src/{{sanitized}}/util.clj" (render "src/mchfc/util.clj" data)]
             ["src/{{sanitized}}/view.clj" (render "src/mchfc/view.clj" data)]
             ["src/ring/middleware/session/httpsession.clj" (render "src/ring/middleware/session/httpsession.clj" data)]
             ["sass/screen.scss" (render "sass/screen.scss")]
             ["sass/partials/_base.scss" (render "sass/partials/_base.scss")]
             ["sass/partials/_layout.scss" (render "sass/partials/_layout.scss")]
             ["sass/partials/_skeleton.scss" (render "sass/partials/_skeleton.scss")]
             "/public/css/img" 
             "/public/images" 
             "/public/js" 
             )))
