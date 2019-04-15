<#import "parts/common.ftl" as c>

<@c.page>
    <h2>Sensors</h2>
    <div class="form-row">
        <div class="form-group col-md-6 ">
            <form class="form-inline" method="post" action="/filter/sensors">
                 <input class="form-control" type="text" name="filterSensor" placeholder="Search for a sensor">
                 <button type="submit" class="btn btn-primary ml-2">Search</button>
            </form>
        </div>
    </div>
     <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
            Add new sensor
     </a>
    <div class="collapse" id="collapseExample">
        <div class="form-group mt-3">
            <form method="post" action="/add/sensor">
                <div class="form-group">
                    <input class="form-control" type="text" name="name" placeholder="Name" required/>
                </div>
                <div class="form-group">
                    <input class="form-control" type="text" name="modelSensor" placeholder="Model" required />
                </div>
                <div class="form-group">
                    <input class="form-control" type="number" name="serialNumber" placeholder="Serial number" required/>
                </div>
                <div class="form-group">
                    <button class="btn btn-primary" type="submit">Add</button>
                </div>
            </form>
        </div>
    </div>
    <div class="card-columns">
         <#list sensors as sensor>
             <div class="card my-3">
                <h5 class="card-header text-muted">
                    ${sensor.name}
                </h5>
                <div class="card-body m-2">
                    <p class="card-title inline"><strong> Id:</strong>  ${sensor.id}</p>
                    <p class="card-text"><strong>Model:</strong> ${sensor.model}</p>
                    <p class="card-text"><strong>Serial number: </strong>${sensor.serialNumber}</p>
                </div>
             </div>
                <#else>
                    No sensors
         </#list>
     </div>
</@c.page>