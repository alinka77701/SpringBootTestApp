<#import "parts/common.ftl" as c>

<@c.page>
    <h2>Buildings</h2>
    <div class="form-row">
        <div class="form-group col-md-6 ">
            <form class="form-inline" method="post" action="/filter/buildings">
                 <input class="form-control" type="text" name="filterBuilding" placeholder="Search for a building">
                 <button type="submit" class="btn btn-primary ml-2">Search</button>
            </form>
        </div>
    </div>
     <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
            Add new building
     </a>
    <div class="collapse" id="collapseExample">
        <div class="form-group mt-3">
            <form method="post" action="/add/building">
                <div class="form-group">
                    <input class="form-control" type="text" name="name" placeholder="Name" required/>
                </div>
                <div class="form-group">
                    <input class="form-control" type="text" name="address" placeholder="Address" required/>
                </div>
                <div class="form-group">
                    <button class="btn btn-primary" type="submit">Add</button>
                </div>
            </form>
        </div>
    </div>
    <div class="card-columns">
         <#list buildings as building>
             <div class="card my-3">
                <h5 class="card-header text-muted">
                    ${building.name}
                </h5>
                <div class="card-body m-2">
                    <p class="card-title inline"><strong> Id:</strong>  ${building.id}</p>
                    <p class="card-text"><strong>Address:</strong> ${building.address}</p>
                </div>
             </div>
                <#else>
                    No buildings
         </#list>
     </div>
</@c.page>