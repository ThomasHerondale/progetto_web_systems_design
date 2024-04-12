class Advertisement {
    constructor(
        title,
        duration,
        description,
        url
    ) {
        this.title = title;
        this.duration = duration;
        this.description = description;
        this.url = url;
    }
}

function parseSchedule(xml) {
    let wall = xml.childNodes[1].getElementsByTagName('advertisement')
    let ads = []
    for (let i = 0; i < wall.length; i++) {
        let ad = wall[i]
        let title = ad.getElementsByTagName('title')[0].firstChild.nodeValue
        let duration = ad.getElementsByTagName('duration')[0].firstChild.nodeValue
        let description = ad.getElementsByTagName('description')[0].firstChild.nodeValue
        let url = ad.getElementsByTagName('url')[0].firstChild.nodeValue
        ads[i] = new Advertisement(
            title,
            duration,
            description,
            url
        )
    }
    console.log(ads);
    return ads
}