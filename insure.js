document.addEventListener("DOMContentLoaded", 
				function(event) {
	addAllMyListeners();
});

function addAllMyListeners(){
	// Add your functions here
	document.getElementById("Add_item").addEventListener("click",AddItem);	
	
}

function AddItem(){
	var count = document.getElementById("items_table").rows.length;
	var row = document.getElementById("items_table").insertRow(count);
	var inputList = document.getElementsByTagName("input");
	var cell0 = row.insertCell(0);
	var cell1 = row.insertCell(1);
	var cell2 = row.insertCell(2);
	var cell3 = row.insertCell(3);
	var cell4 = row.insertCell(4);

	cell0.style.textAlign = "center";
	cell1.style.textAlign = "center";
	cell2.style.textAlign = "center";
	cell3.style.textAlign = "center";
	cell4.style.textAlign = "center";

	cell0.innerHTML = inputList['1'].value;
	cell1.innerHTML = inputList['2'].value;
	cell2.innerHTML = inputList['3'].value;
	cell3.innerHTML = inputList['4'].value;
	cell4.innerHTML = inputList['5'].value;



	var op = document.getElementById("ItemSelect");
	var element = document.createElement("option");
	element.innerHTML = inputList['1'].value;
	op.appendChild(element);

}
