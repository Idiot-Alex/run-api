import { terser } from "rollup-plugin-terser";
import filesize from "rollup-plugin-filesize";

import baseConfig from "./rollup.config.base";

export default {
    ...baseConfig,
    plugins: [...baseConfig.plugins, terser(), filesize()],
};