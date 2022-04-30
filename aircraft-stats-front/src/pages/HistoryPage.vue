<template>
    <div>
        <label for="time">Time: </label>
        <select style="margin-bottom: 20px;" id="time" name="time" v-model="time" @change="getFlights(time)">
          <option :key="t" v-for="t in times" :value="t">{{ new Date(parseInt(t.toString() + '000')) }}</option>
        </select>
        <h1>Total aircraft in the sky at {{  new Date(parseInt(time.toString() + '000')) }}: {{ this.flights.length }}</h1>
        <google-map v-if="flights.length > 0" :use-history="true" :history="flights" style-config="width:50%; height: 74vh;" />
        <!-- <li v-bind:key="index" v-for="(item, index) in countryFlights">
          {{ index }}
          {{ item }}
        </li> -->
        <v-table :data="countryFlights">
          <thead slot="head">
            <th>Country</th>
            <th>Nb of flights</th>
          </thead>
          <tbody slot="body" slot-scope="{displayData}">
            <tr v-for="(row, index) in displayData" :key="index">
              <td>{{ index }}</td>
              <td>{{ row }}</td>
            </tr>
        </tbody>
        </v-table>
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
          countryFlights: {}
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
        this.countryFlights = {}
        this.flights.forEach((element) => {
          if (element.latitude !== undefined && element.longitude !== undefined) {
            const result = lookUpRaw(element.latitude, element.longitude)
            if (result !== null) {
              if (this.countryFlights[result.features[0].properties.geonunit]) {this.countryFlights[result.features[0].properties.geonunit] += 1} else {this.countryFlights[result.features[0].properties.geonunit] = 1}
            }
          } else { 
            if (this.countryFlights["undefined"]) {this.countryFlights["undefined"] += 1} else {this.countryFlights["undefined"] = 1}
          }
        })
      }).catch((error) => {
        console.log(error)
      })
    }
  }
}
</script>