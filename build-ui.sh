echo "Building ui ..."
cd ui
ng build --prod
#ng build
cp  dist/*  ../src/main/resources/static/
cd ..
