def call(chartPath, releaseName, imageRepo, imageTag) {

    echo "Deploying application using Helm"

    sh """
    helm upgrade --install ${releaseName} ${chartPath} \
      --set image.repository=${imageRepo} \
      --set image.tag=${imageTag}
    """

}