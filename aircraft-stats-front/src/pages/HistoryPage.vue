<template>
    <div>
        <label for="time">Time: </label>
        <select style="margin-bottom: 20px;" id="time" name="time">
        <option value="now">now</option>
        <option value="1650540944">{{ new Date(1650540944000) }}</option>
        </select>
    </div>
</template>

<script>
// import axios from "axios"


export default {
    data() {
        return {
            markers: []
        };
  },

    mounted() {
        this.getFlights()
    },

    methods: {
    getFlights() {
      // axios.get("http://localhost:9090/flights").then((response) => {
      //   // let i = 0;
      //   response.data.forEach(element => {
      //   //   if (i < 1000) {            
      //       this.markers.push({ lat: element.latitude, lng: element.longitude })
      //       // i += 1
      //   //   }
      //   });
      //   console.log(this.markers)
      // }).catch((error) => {
      //   console.log(error)
      // })
    }
  }
}
</script>