function getWeatherData(hour) {
    fetch("https://api.open-meteo.com/v1/forecast?" +
        "latitude=38.132&" +
        "longitude=13.3356&" +
        "hourly=temperature_2m,relative_humidity_2m,precipitation_probability,weather_code,wind_speed_10m&" +
        "forecast_days=1",
        )
        .then(async r => test(await r.text()))
        .catch(reason => console.error(reason))
}

function test(txt) {
    let json = JSON.parse(txt);
    console.log(json);
    console.log(
        parseWeatherData(json, 17)
    );
}

class WeatherData {
    constructor(
        weatherCode,
        temperature,
        humidity,
        precipitationProbability,
        windSpeed) {
        let o = parseWeatherCode(weatherCode)
        this.description = o.desc
        this.imgPath = o.img_path

        this.temperature = temperature
        this.humidity = humidity
        this.precipitationProbability = precipitationProbability
        this.windSpeed = windSpeed
    }
}

function parseWeatherData(json, hour) {
    console.assert(0 <= hour <= 23)
    json = json['hourly']
    let weatherCode = json['weather_code'][hour]
    let temperature = json['temperature_2m'][hour]
    let humidity = json['relative_humidity_2m'][hour]
    let precipitationProbability = json['precipitation_probability'][hour]
    let windSpeed = json['wind_speed_10m'][hour]

    return new WeatherData(
        weatherCode,
        temperature,
        humidity,
        precipitationProbability,
        windSpeed,
    )
}


function parseWeatherCode(code) {
    switch (code) {
        case 0:
            return {desc: "Clear Sky", img_path: 'images/weather/ic_sunny.svg'};
        case 1:
            return {desc: "Mainly Clear", img_path: 'images/weather/ic_cloudy.svg'};
        case 2:
            return {desc: "Partly Cloudy", img_path: 'images/weather/ic_cloudy.svg'};
        case 3:
            return {desc: "Overcast", img_path: 'images/weather/ic_cloudy.svg'};
        case 45:
            return {desc: "Foggy", img_path: 'images/weather/ic_very_cloudy.svg'};
        case 48:
            return {desc: "Depositing Rime Fog", img_path: 'images/weather/ic_very_cloudy.svg'};
        case 51:
            return {desc: "Light Drizzle", img_path: 'images/weather/ic_rainshower.svg'};
        case 53:
            return {desc: "Moderate Drizzle", img_path: 'images/weather/ic_rainshower.svg'};
        case 55:
            return {desc: "Dense Drizzle", img_path: 'images/weather/ic_rainshower.svg'};
        case 56:
            return {desc: "Light Freezing Drizzle", img_path: 'images/weather/ic_snowyrainy.svg'};
        case 57:
            return {desc: "Dense Freezing Drizzle", img_path: 'images/weather/ic_snowyrainy.svg'};
        case 61:
            return {desc: "Slight Rain", img_path: 'images/weather/ic_rainy.svg'};
        case 63:
            return {desc: "Moderate Rain", img_path: 'images/weather/ic_rainy.svg'};
        case 65:
            return {desc: "Heavy Rain", img_path: 'images/weather/ic_rainy.svg'};
        case 66:
            return {desc: "Light Freezing Drizzle", img_path: 'images/weather/ic_snowyrainy.svg'};
        case 67:
            return {desc: "Heavy Freezing Rain", img_path: 'images/weather/ic_snowyrainy.svg'};
        case 71:
            return {desc: "Slight Snow Fall", img_path: 'images/weather/ic_snowy.svg'};
        case 73:
            return {desc: "Moderate Snow Fall", img_path: 'images/weather/ic_heavysnow.svg'};
        case 75:
            return {desc: "Heavy Snow Fall", img_path: 'images/weather/ic_heavysnow.svg'};
        case 77:
            return {desc: "Snow Grains", img_path: 'images/weather/ic_heavysnow.svg'};
        case 80:
            return {desc: "Slight Rain Showers", img_path: 'images/weather/ic_rainshower.svg'};
        case 81:
            return {desc: "Moderate Rain Showers", img_path: 'images/weather/ic_rainshower.svg'};
        case 82:
            return {desc: "Violent Rain Showers", img_path: 'images/weather/ic_rainshower.svg'};
        case 85:
            return {desc: "Slight Snow Showers", img_path: 'images/weather/ic_snowy.svg'};
        case 86:
            return {desc: "Heavy Snow Showers", img_path: 'images/weather/ic_snowy.svg'};
        case 95:
            return {desc: "Moderate Thunderstorm", img_path: 'images/weather/ic_thunder.svg'};
        case 96:
            return {desc: "Slight Hail Thunderstorm", img_path: 'images/weather/ic_rainythunder.svg'};
        case 99:
            return {desc: "Heavy Hail Thunderstorm", img_path: 'images/weather/ic_rainythunder.svg'};
    }
}
