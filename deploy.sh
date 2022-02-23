set -e
mkdir -p deploy
cd deploy
cp -r ../out ./
git init
git add -A
git commit -m "deploy"
git push -f https://github.com/Hagenbo/TopSecretOthelloProject master:deploy
cd -
rm -rf deploy

