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
import GoogleMap from "../components/GoogleMap"
import { lookUpRaw } from "geojson-places"

export default {
    data() {
        return {
          time: '',
          times: [],
          flights: [],
          countryFlights: []
        };
  },

  components: {
    GoogleMap
  },

  async mounted() {
    await this.getTimes()
    this.time = this.times[0]
    await this.getFlights(this.times[0])
    this.startInterval()
  },

  methods: {
    startInterval() {
      this.intervalid1 = setInterval(function(){
        this.getTimes()
      }.bind(this), 10000);
    },

    async getTimes() {
      await axios.get('http://localhost:9090/history/time').then((response) => {
        this.times= []
        response.data.forEach((time) => {
          this.times.push(time);
        })
        console.log(this.times)
      }).catch((error) => {
        console.log(error)
      })
    },

    getFlights(time) {
      axios.get(`http://localhost:9090/history/time/${time}`).then((response) => {
        this.flights = response.data
        this.flights.forEach((element) => {
          this.countryFlights.push(lookUpRaw(element.latitude, element.longitude)?.features[0].properties.geonunit)
        })
        console.log(this.countryFlights)
      }).catch((error) => {
        console.log(error)
      })
    }
  }
}
</script>