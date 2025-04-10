**To get going**

* On local: 
  * Ensure the `.env` file has `ENV=dev` or `ENV=prod` as needed. 
  * To run tests: `mvn clean test`
  * To generate the allure report: 
  `allure generate target/allure-results --clean -o target/allure-report`
  * To open the report in a browser:
  `allure open target/allure-report`

* On Github:
  * Be aware that the Secrets and Variables used for the tests are here:
    * repo > Settings > Secrets and variables > Secrets tab has the user credentials, and the Variables tab has the repo variables like ENV, BASE_URL_PROD, and BASE_URL_DEV
  * The `.github/workflows/ci.yml` file takes care to run the tests automatically on every push.
  * To run tests when desired - go to Actions > All workflows > open the latest successful run on `master` branch > Re-run all jobs
  * To see the report on Github pages: 
    * All workflows > open `pages build and deployment` in `gh-pages`branch > click the link under `deploy`
    * The allure report will be here: https://amitshokeen.github.io/PorticoTennisApp_API_TestAutomation/
  * The report can also be downloaded to local system from Artifacts.

* Tips for deploying the report on Github Pages:
  1. repo > settings > Actions > General > Workflow permissions > select `Read and write permissions`
  2. repo > settings > Pages > Build and deployment > Deploy from a branch > gh-pages > / (root)
  3. See the step named `Deploy Allure report to GitHub Pages` in the ci.yml file.
