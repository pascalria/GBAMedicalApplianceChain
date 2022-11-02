pragma solidity ^0.4.0;

contract patientfile {
 
    mapping(address => mapping(uint => Patient)) stores;
    mapping(uint => address) patientIdInStore;
 
    struct Patient{
        uint id ;
        string name ;
        uint age ;
        string[] prescription;
    }

    Patient patient;

   function addpatient(uint id1,string name1,uint age1) public {
       require(patientIdInStore[id1] == address(0));
       Patient memory pt1 = patient;
       pt1.id = id1;
       pt1.name = name1;
       pt1.age = age1;
       stores[msg.sender][id1]=pt1;
       patientIdInStore[id1] = msg.sender;
   }
/*
   function pushhash(string hash,uint id) public returns(bool){
       Patient memory pt = stores[patientIdInStore[id]][id];
       pt.prescription.push(hash);
       stores[msg.sender][id]=pt;
   }
*/
   function getpatient(uint id) view public returns(uint,string,uint,address){
       Patient memory pt = stores[patientIdInStore[id]][id];
       address addr2 = patientIdInStore[id];
       return(pt.id,pt.name,pt.age,addr2);
   }

    function testfunction(string namet) public returns(uint,string,uint,address){
        uint idt = 440300200001010001;
        uint aget = 1;
        addpatient(idt,namet,aget);
        return(getpatient(idt));
    }

}
