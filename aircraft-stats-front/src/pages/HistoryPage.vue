<template>
    <div>
        <label for="time">Time: </label>
        <select style="margin-bottom: 20px;" id="time" name="time" v-model="time" @change="getFlights(time)">
          <option :key="t" v-for="t in times" :value="t">{{ new Date(parseInt(t.toString() + '000')) }}</option>
        </select>
        <h1>Total aircraft in the sky at {{  new Date(parseInt(time.toString() + '000')) }}: {{ this.flights.length }}</h1>
    </div>
</template>

<script>
import axios from "axios"

export default {
    data() {
        return {
          time: '',
          times: [],
          flights: []
        };
  },

    async mounted() {
      await axios.get('http://localhost:9090/history/time').then((response) => {
        response.data.forEach((time) => {
          this.times.push(time);
        })
        console.log(this.times)
      }).catch((error) => {
        console.log(error)
      })
      this.time = this.times[0]
      this.getFlights(this.times[0])
    },

    methods: {
      getFlights(time) {
        axios.get(`http://localhost:9090/history/time/${time}`).then((response) => {
          this.flights = response.data
          console.log(this.flights)
        }).catch((error) => {
          console.log(error)
        })
      }
  }
}
</script>