(ns rest.file
  (:use compojure.core))

(def file-one {
  :parent "root"
  :id 1
  :name "photo 7.JPG"
  :size 366662
  :content-type "image/jpeg"
  :url "http://rtcalpha:7000/api/v1/file/526b7f61-411a-4e70-83bb-c1201d3c29b3/download"
  :created-on "2013-05-23T20:57:29.651Z"})

(def file-two {
  :parent "root"
  :id 2
  :name "photo 1.JPG"
  :size 366662
  :content-type "image/jpeg"
  :url "http://rtcalpha:7000/api/v1/file/436b7f61-411a-4e70-83bb-c1201d3c29c7/download"
  :created-on "2013-07-23T20:57:29.651Z"})

(def files (atom [file-one file-two]))

(defn body [content] {:body content})

(defn all-files [] (body @files))

(defn get-existing-file [id]
  (first (filter #(= (str (:id %)) id) @files)))

(defn file [id]

  (let [f (get-existing-file id)]
    (if f
      (body f)
      (body "Does not exist"))))

(defn get-file [req]
    (let [id (:id (:route-params req))]
      (if id
        (file id)
        (all-files))))

(defn save-file [file]
  (swap! files conj file)
  (body file))

(defn post-file [req]
  (let [id (:id (:params req))
        f (get-existing-file (:id (:params req)))]
    (if f
        (body "Record already exists")
        (save-file (:params req))
        ; (body "Does not exist yet")
      )))

(defn put-file [{:keys [route-params]}]
  (println (:id route-params)))

(defn delete-file [{:keys [route-params]}]
  (println (:id route-params)))

(defroutes file-routes
  (GET "/" [] get-file)
  (GET "/:id" [id] get-file)
  (POST "/" [] post-file)
  (PUT "/:id" [id] put-file)
  (DELETE "/:id" [id] delete-file))