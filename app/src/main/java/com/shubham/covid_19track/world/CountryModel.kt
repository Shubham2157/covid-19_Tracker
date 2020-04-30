package com.shubham.covid_19track.world

class CountryModel {
    var flag: String? = null
    var country: String? = null
    var cases: String? = null
    var todayCases: String? = null
    var deaths: String? = null
    var todayDeaths: String? = null
    var recovered: String? = null
    var active: String? = null
    var critical: String? = null

    constructor() {}


    constructor(flag: String?, country: String?, cases: String?, todayCases: String?, deaths: String?, todayDeaths: String?, recovered: String?, active: String?, critical: String?) {
        this.flag = flag
        this.country = country
        this.cases = cases
        this.todayCases = todayCases
        this.deaths = deaths
        this.todayDeaths = todayDeaths
        this.recovered = recovered
        this.active = active
        this.critical = critical
    }

}