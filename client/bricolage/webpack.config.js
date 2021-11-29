const path = require ("path");
const webpack = require ('webpack');
const WebpackDevServer = require("webpack-dev-server");

module.exports = {
    entry: './src/index.js',
    mode: 'development',
    module: {
        rules : [
            {
                test : /\.(js|jsx)$/,
                exclude : /(node_modules)/,
                loader : 'babel-loader',
                options : {
                    presets : ['@babel/env']
                }
            },
            {
                test : /\.css$/,
                use : [ 'style-loader', 'css-loader'] 
            }
        ]
    },
    resolve: { 
        extensions: ['*', '.js', '.jsx']
    },
    output: {
        path: path.resolve (__dirname, 'dist/'),
        publicPath: '/dist/',
        filename: 'main.js'
    },
    devServer: {
        allowedHosts: 'all',
        bonjour: true,
        client: {
            logging: 'verbose',
            overlay: true,
            progress: true,
            reconnect: true            
        },
        open: ['/public/index.html'],
        static: {
            directory: path.join(__dirname, 'public'),
        },
        port: 3000,
        host: 'localhost'
    },
    plugins: [
        new webpack.HotModuleReplacementPlugin()
    ]
}
