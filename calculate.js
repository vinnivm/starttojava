 function calculatedate()
                {
                        let inputdate=document.getElementById("date1id").value;
                        let holiday=moment(document.getElementById("holidayid").value).format("DD/MM/YYYY");
                        let nod=document.getElementById("nod1").value;
                        let inputdatetemp;
                        let day;
                        for ( let i=1 ;i<=nod ; i++)
                        {
                            inputdatetemp=moment(inputdate).add(i,'day').format("DD/MM/YYYY");
                            if( holiday == inputdatetemp )
                                {
                                    nod=parseInt(nod)+1;
                                    i++;
                                }
                            day=moment(inputdate).add(i,'day').format('dddd');
                            if( day == "Saturday" )
                                nod=parseInt(nod)+1;
                            else if( day == "Sunday" )
                                nod=parseInt(nod)+1;                                         
                        }
                        let newdate=moment(inputdate).add(nod,'day').format("DD/MM/YYYY");
                        document.getElementById("outputid1").value="Date: "+newdate+" , "+day;
                }
                function calculatedate1()
                {
                    var xhttp = new XMLHttpRequest();
                    let inputdate=document.getElementById("date1id").value;
                    let holiday=document.getElementById("holidayid").value;
                    let nod=document.getElementById("nod1").value;
                    xhttp.onreadystatechange = function() {
                    console.log(this);
                    if (this.readyState == 4 && this.status == 200)
                    {
                        console.log(this.responseText)
                        document.getElementById("outputid1").value='';
                        document.getElementById("outputid1").value=this.responseText;
                    }
                    };
                    xhttp.open("POST", "http://localhost:8080/starttojava/holiday?inputdate="+ inputdate +"&holiday="+ holiday +"&nod="+ nod, true);
                    xhttp.send();
                }