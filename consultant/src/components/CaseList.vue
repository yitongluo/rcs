<template>
  <div class="list row">
    <div class="col-md-8">
      <div class="input-group mb-3">
        <input type="text" class="form-control" placeholder="Search by candidate name"
          v-model="candidateName"/>
        <div class="input-group-append">
          <button class="btn btn-outline-secondary" type="button"
            @click="search"
          >
            Search
          </button>
        </div>
      </div>
    </div>
    <div class="col-md-6">
      <h4>Cases List</h4>
      <ul class="list-group">
        <li class="list-group-item"
          :class="{ active: index == currentIndex }"
          v-for="(Case, index) in Cases"
          :key="index"
          @click="setActiveCase(Case, index)"
        >
          {{ Case.candidateName }}
        </li>
      </ul>

      <button class="m-3 btn btn-sm btn-danger" @click="removeAllCases">
        Remove All
      </button>
    </div>
    <div class="col-md-6">
      <div v-if="currentCase">
        <h4>Case</h4>
        <div>
          <label><strong>candidateName:</strong></label> {{ currentCase.candidateName }}
        </div>
        <div>
          <label><strong>accessCode:</strong></label> {{ currentCase.accessCode }}
        </div>
        <div>
          <label><strong>Status:</strong></label> {{ currentCase.published ? "Published" : "Pending" }}
        </div>

        <router-link :to="'/Cases/' + currentCase.id" class="badge badge-warning">Edit</router-link>
      </div>
      <div v-else>
        <br />
        <p>Please click on a Case...</p>
      </div>
    </div>
  </div>
</template>

<script>
import CaseDataService from "../services/CaseDataService";
export default {
  name: "Cases-list",
  data() {
    return {
      Cases: [],
      currentCase: null,
      currentIndex: -1,
      candidateName: ""
    };
  },
  methods: {
    retrieveCases() {
      CaseDataService.getAll()
        .then(response => {
          this.Cases = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    refreshList() {
      this.retrieveCases();
      this.currentCase = null;
      this.currentIndex = -1;
    },
    setActiveCase(inputcase, index) {
      this.currentCase = inputcase;
      this.currentIndex = inputcase ? index : -1;
    },
    removeAllCases() {
      CaseDataService.deleteAll()
        .then(response => {
          console.log(response.data);
          this.refreshList();
        })
        .catch(e => {
          console.log(e);
        });
    },
    
    searchName() {
      CaseDataService.findByName(this.candidateName)
        .then(response => {
          this.Cases = response.data;
          this.setActiveCase(null);
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    }
  },
  mounted() {
    this.retrieveCases();
  }
};
</script>

<style>
.list {
  text-align: left;
  max-width: 750px;
  margin: auto;
}
</style>