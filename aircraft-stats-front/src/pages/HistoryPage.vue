<template>
    <div>
        <label for="time">Time: </label>
        <select style="margin-bottom: 20px;" id="time" name="time" v-model="time" @change="getFlights(time)">
          <option :key="t" v-for="t in times" :value="t">{{ new Date(parseInt(t.toString() + '000')) }}</option>
        </select>
        <h1>Total aircraft in the sky at {{  new Date(parseInt(time.toString() + '000')) }}: {{ this.flights.length }}</h1>
        <!-- <google-map v-if="flights.length > 0" :use-history="true" :history="flights" style-config="width:50%; height: 74vh;" /> -->
        <!-- <li v-bind:key="index" v-for="(item, index) in countryFlights">
          {{ index }}
          {{ item }}
        </li> -->
        <grid-layout
          :layout.sync="layout"
          :col-num="12"
          :row-height="30"
          :is-draggable="false"
          :is-resizable="false"
          :is-mirrored="false"
          :vertical-compact="false"
          :margin="[0, 0]"
          :use-css-transforms="true"
        >
          <grid-item v-for="item in layout"
            :x="item.x"
            :y="item.y"
            :w="item.w"
            :h="item.h"
            :i="item.i"
            :key="item.i"
          >
            <google-map 
              v-if="flights.length > 0 && item.i === '0'" 
              :use-history="true" 
              :history="flights" 
              style-config="width:100%; height: 74vh;" />
            <div 
              v-else 
              v-bind:key="index" 
              v-for="(item, index) in countryFlights"
              style="text-align: left;"
            >
              <p style="margin-left: 10px;">{{`${index}: ${item}`}}</p>
            </div>
        </grid-item>
        </grid-layout>
    </div>
</template>

<script>
import axios from "axios"
import GoogleMap from "../components/GoogleMap"
import { lookUpRaw } from "geojson-places"
import VueGridLayout from 'vue-grid-layout';

export default {
    data() {
        return {
          time: '',
          times: [],
          flights: [],
          countryFlights: {},
          layout: [
            {"x":0,"y":0,"w":9,"h":2,"i":"0"},
            {"x":9,"y":0,"w":3,"h":4,"i":"1"},
          ]
        };
  },

  components: {
    GoogleMap,
    GridLayout: VueGridLayout.GridLayout,
    GridItem: VueGridLayout.GridItem
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