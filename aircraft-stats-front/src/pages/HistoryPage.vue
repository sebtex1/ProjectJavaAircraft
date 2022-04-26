<template>
    <div>
        <label for="time">Time: </label>
        <select style="margin-bottom: 20px;" id="time" name="time" v-model="time" @change="getFlights(time)">
          <option :key="t" v-for="t in times" :value="t">{{ new Date(parseInt(t.toString() + '000')) }}</option>
        </select>
        <h1>Total aircraft in the sky at {{  new Date(parseInt(time.toString() + '000')) }}: {{ this.flights.length }}</h1>
        <google-map v-if="flights.length > 0" :use-history="true" :history="flights" />
    </div>
</template>

<script>
import axios from "axios"
import GoogleMap from "../components/GoogleMap";

export default {
    data() {
        return {
          time: '',
          times: [],
          flights: []
        };
  },

  components: {
    GoogleMap
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
    await this.getFlights(this.times[0])
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