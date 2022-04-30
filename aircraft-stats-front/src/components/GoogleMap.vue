<template>
  <div> 
    <!-- Personnal custom map by adding mapId -->
    <gmap-map
      :center="center"
      :zoom="3"
      :style="styleConfig"
      :options="{
        mapTypeControl: false,
        streetViewControl: false,
        rotateControl: false,
        fullscreenControl: false,
        scaleControl: false,
        mapId: '6ba101fdd85bc7ec'
      }"
    >
      <gmap-custom-marker :key="m.icao24" v-for="(m) in markers" :marker="m">
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

  props: {
    useHistory: { type: Boolean, default: false },
    history: { type: [Array], default: () => [] },
    styleConfig: { type: String, default: "width:100%;  height: 50rem;" }
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
    if (this.useHistory === true) {
      this.processHistory()
    } else {
      this.getFlights()
    }
    // console.log(result)
  },

  watch: {
    history: {
      handler: function (newval, oldval) {
        if (newval !== oldval) {this.processHistory()}
      },
      immediate: true,
      deep: true
    }
  },
 
  methods: {
    getFlights() {
      this.intervalid1 = setInterval(function(){
        this.markers = []
        axios.get("http://localhost:9090/flights").then((response) => {
          // let i = 0;
          response.data.forEach(element => {
            // if (i < 1000) {
              this.markers.push({ id: element.icao24, lat: element.latitude, lng: element.longitude, rotation: element.trueTrack.toFixed(0) })
            //   i += 1
            // }
          });
          console.log('flights', this.markers)
        }).catch((error) => {
          console.log(error)
        })}
      .bind(this), 10000);
    },

    processHistory() {
      this.markers = []
      this.history.forEach(element => {
        this.markers.push({ id: element.icao24, lat: element.latitude, lng: element.longitude })
      });
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