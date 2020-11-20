/**
 *To manage multi language settings
 *
 * @interface Language
 */
export interface Language {
    /**
     *The display name of tje language
     *
     * @type {string}
     * @memberof Language
     */
    name: string;

    /**
     *A valid IETF language tag
     *(https://datahub.io/core/language-codes/r/3.html)
     *These will also be used for api calls!
     *
     * @type {string}
     * @memberof Language
     */
    code: string;

    /**
     *Path to the local flag icon
     *Icons are open source. Read more in `src/assets/country-flags/.README.md`
     *
     * @type {string}
     * @memberof Language
     */
    flag: string;

    /**
     *To set the default language
     *
     * @type {boolean}
     * @memberof Language
     */
    isDefault?: boolean;
}
