class Facility {
    constructor(id, description, latitude, longitude, status, schedule_id) {
        this.id = id
        this.description = description
        this.latitude = latitude
        this.longitude = longitude
        this.status = status
        this.schedule_id = schedule_id
    }
}

class Schedule {
    constructor(id, file_path) {
        this.id = id
        this.file_path = file_path
    }
}

function getFacilities(onSuccess, onError) {
    $.get("http://127.0.0.1:8080/facilities",
        {},
        function (data, status) {
            if (status !== "200") {
                onError()
            } else {
                let facilities = []
                for (let i = 0; i < data.length; i++) {
                    let datum = data[i]
                    facilities[i] = new Facility(
                        datum["id"],
                        datum["description"],
                        datum["latitude"],
                        datum["longitude"],
                        datum["status"],
                        datum["schedule_id"]
                    )
                }
                onSuccess(facilities)
            }
        }
    )
}

function getSchedules(onSuccess, onError) {
    $.get("http://127.0.0.1:8080/schedules",
        {},
        function (data, status) {
            if (status !== "200") {
                onError()
            } else {
                let schedules = []
                for (let i = 0; i < data.length; i++) {
                    let datum = data[i]
                    schedules[i] = new Schedule(
                        datum["id"],
                        datum["file_path"],
                    )
                }
                onSuccess(schedules)
            }
        }
    )
}

function updateFacility(facility, onSuccess, onError) {
    $.ajax(`http://127.0.0.1:8080/facilities/${facility.id}`,
        {
            method: 'PUT',
            dataType: "json",
            data: facility,
            success: onSuccess,
            error: onError
        }
    )
}

function addFacility(facility, onSuccess, onError) {
    $.post(`http://127.0.0.1:8080/facilities`,
        facility,
        function (data, status) {
            if (status !== "200")
                onError()
            else
                onSuccess()
        },
        "json"
    )
}