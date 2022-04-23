<template>
  <div> 
    <!-- Personnal custom map by adding mapId -->
    <gmap-map
      :center="center"
      :zoom="10"
      style="width:100%;  height: 50rem;"
      :options="{
        mapTypeControl: false,
        streetViewControl: false,
        rotateControl: false,
        fullscreenControl: false,
        scaleControl: false,
        mapId: '6ba101fdd85bc7ec'
      }"
    >
      <gmap-custom-marker :key="index" v-for="(m, index) in markers" :marker="m">
        <!-- 0° = north -->
        <img :style="`width: 80%; transform: rotate(${m.rotation}deg);`" :src="aircraftIcon"/>
      </gmap-custom-marker>
    </gmap-map>
 
  </div>
</template>
 
<script>
import Aircraft from "../assets/Aircraft.png"
import axios from "axios"
import GmapCustomMark from "vue2-gmap-custom-marker"

export default {
  name: "GoogleMap",

  components: {
    'gmap-custom-marker': GmapCustomMark
    // 'cluster': GmapCluster
  },

  data() {
    return {
      center: { lat: 48.866667, lng: 2.333333 },
      markers: [],
      currentPlace: null,
      aircraftIcon: Aircraft
    };
  },
 
  mounted() {
    this.getFlights();
  },
 
  methods: {
    getFlights() {
      axios.get("http://localhost:9090/flights").then((response) => {
        // let i = 0;
        response.data.forEach(element => {
          // if (i < 1000) {
            this.markers.push({ lat: element.latitude, lng: element.longitude, rotation: element.trueTrack.toFixed(0) })
          //   i += 1
          // }
        });
        console.log(this.markers)
      }).catch((error) => {
        console.log(error)
      })
    }
  }
};
</script>

<style scoped>
.aircraft {
  width: 100%;
  /* filter: invert(100%) sepia(100%) saturate(0%) hue-rotate(303deg) brightness(103%) contrast(101%); */
}
</style>