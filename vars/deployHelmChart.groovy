def call(chartPath, releaseName, imageRepo, imageTag) {

    echo "Deploying application using Helm"

    sh """
    helm uninstall ${releaseName} -n demo || true
         sleep 60
    helm upgrade --install ${releaseName} ${chartPath}  --namespace demo \
      --set image.repository=${imageRepo} \
      --set image.tag=${imageTag}
    """

}