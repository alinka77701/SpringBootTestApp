<#import "parts/common.ftl" as c>

<@c.page>
    <h2>Values</h2>
    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
                Add new value
         </a>
        <div class="collapse" id="collapseExample">
            <div class="form-group mt-3">
                <form method="post" action="/add/temperature">
                    <div class="form-group">
                        <select class="custom-select form-control" name="building">
                          <option selected value="-777">Select building</option>
                           <#list buildings as building>
                            <option value="${building.id}"> ${building.name}</option>
                               <#else>
                                   none
                           </#list>
                        </select>
                    </div>
                    <div class="form-group">
                        <select class="custom-select form-control" name="sensor">
                          <option selected value="-777">Select sensor</option>
                            <#list sensors as sensor>
                             <option value="${sensor.id}"> ${sensor.model}</option>
                                <#else>
                                    none
                            </#list>
                        </select>
                    </div>
                    <div class="form-group">
                         <input type="date" class="form-control" name="dateInString">
                    </div>
                    <div class="form-group">
                     <input class="form-control" type="number" name="temperature" placeholder="Temperature" required/>
                    </div>
                    <div class="form-group">
                        <button class="btn btn-primary" type="submit">Add</button>
                    </div>
                </form>
            </div>
        </div>
     <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample1" role="button" aria-expanded="false" aria-controls="collapseExample1">
            Filter
     </a>
    <div class="collapse" id="collapseExample1">
        <div class="form-group mt-3">
            <form method="post" action="/filter/temperature">
                 <div class="form-group">
                    <select class="custom-select form-control" name="buildingId">
                      <option selected value="-1">Select building</option>
                       <#list buildings as building>
                        <option value="${building.id}"> ${building.name}</option>
                           <#else>
                               none
                       </#list>
                    </select>
                </div>
                <div class="form-group">
                    <select class="custom-select form-control" name="sensorId">
                      <option selected value="-1">Select sensor</option>
                        <#list sensors as sensor>
                         <option value="${sensor.id}"> ${sensor.model}</option>
                            <#else>
                                none
                        </#list>
                    </select>
                </div>
                <div class="form-group">
                    <input type="date" class="form-control" name="fromDate" >
                </div>
                <div class="form-group">
                    <input type="date" class="form-control" name="toDate" >
                </div>
                <div class="form-group">
                    <button class="btn btn-primary" type="submit">Search</button>
                </div>
            </form>
        </div>
    </div>

    <form method="post" action="/calculate_average">
        <button type="submit" class="btn btn-secondary">Calculate average</button>
    </form>
    <table class="table">
      <thead>
        <tr>
          <th scope="col">#</th>
          <th scope="col">Building</th>
          <th scope="col">Sensor</th>
          <th scope="col">Time</th>
          <th scope="col">Temperature</th>
        </tr>
      </thead>
      <tbody>
     <#list temperatures as temperature>
        <tr>
          <th scope="row">${temperature?counter}</th>
          <td>${temperature.getBuildingName()}</td>
          <td>${temperature.getSensorName()}</td>
           <td>${temperature.time}</td>
          <td>${temperature.value}</td>
        </tr>
            <#else>
             <td>No values</td>
     </#list>
      </tbody>
    </table>
</@c.page>