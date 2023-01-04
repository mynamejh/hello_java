<template>
    <table>
        <thead>
            <tr>
                <th>사원번호</th>
                <th>이름</th>
                <th>성</th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="emp in empList" v-bind:key="emp.employeeId">
                <td>{{emp.employeeId}}</td>
                <td>{{emp.firstName}}</td>
                <td>{{emp.lastName}}</td>
            </tr>
        </tbody>
    </table>
</template>
<script>
export default{
    name:'EmpList',
    data: function(){
        return{
            empList:{}
        }
    },
    created:function(){
        fetch('http://localhost:8095/empInfoList')
        .then(response=>response.json())
        .then(data=>{
            console.log(data);
            this.empList=data;
        })
        .catch(error=>console.log(error));
    }
}

</script>
