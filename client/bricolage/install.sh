printf "install npm\n"
npm install -g npm

printf "npm version: "
npm -v

printf "\n"


mkdir public
mkdir src

printf "Creation index.html\n"
> src/index.html
printf "Remplir index.html\n"

printf "JS6\n"
npm install --save-dev @babel/core @babel/cli @babel/preset-env @babel/preset-react

> .babelrc
> src/App.css
> src/App.js
> src/index.js


npm install react react-dom
npm install --save_dev webpack webpack-cli webpack-dev-server style-loader css-loader babel-loader

> webpack.config.js

npm install i18next react-i18next --save
npm install react-router react-router-dom --save

npm init -y 

npx webpack-dev-server --mode development
npx webpack build -w
