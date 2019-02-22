
Dev Apps Switcher
------

![](https://img.shields.io/npm/v/@jimengio/dev-switcher.svg)

> a devtool.

### Usage

Add a `webpack.dev.js` which is:

```js
let path = require("path");

module.exports = {
  resolve: {
    alias: {
      "pages/apps": path.join(__dirname, "ts/pages/dev-apps"),
    },
  },
};
```

`dev-apps.ts` is the forked file which is a mirror of `app.ts` that this tool would modify:

```bash
yarn global add @jimengio/dev-switcher

# back to project root directory
dev-switcher
```

### Workflow

https://github.com/Cumulo/cumulo-workflow

### License

MIT
