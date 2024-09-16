## Spring Oracle Example
### Web
#### Build
```
export PROJECT_ID=$(gcloud config list --format 'value(core.project)')
gradle build
gcloud builds submit --tag gcr.io/$PROJECT_ID/web-restful .
gcloud run deploy web-restful \
--image gcr.io/$PROJECT_ID/web-restful \
--platform managed \
--region asia-northeast1 \
--tag=web -q

```