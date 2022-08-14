<template>
  <div class="submit-form">
    <div v-if="!submitted">
      <div class="form-group">
        <label for="candidateName">candidateName</label>
        <input
          type="text"
          class="form-control"
          id="candidateName"
          required
          v-model="Case.candidateName"
          name="candidateName"
        />
      </div>

      <div class="form-group">
        <label for="accessCode">accessCode</label>
        <input
          class="form-control"
          id="accessCode"
          required
          v-model="Case.accessCode"
          name="accessCode"
        />
      </div>

      <button @click="saveCase" class="btn btn-success">Submit</button>
    </div>

    <div v-else>
      <h4>You submitted successfully!</h4>
      <button class="btn btn-success" @click="newCase">Add</button>
    </div>
  </div>
</template>

<script>
import CaseDataService from "../services/CaseDataService";
export default {
  name: "add-case",
  data() {
    return {
      Case: {
        id: null,
        candidateName: "",
        accessCode: "",
        published: false
      },
      submitted: false
    };
  },
  methods: {
    saveCase() {
      var data = {
        candidateName: this.Case.candidateName,
        accessCode: this.Case.accessCode
      };
      CaseDataService.create(data)
        .then(response => {
          this.Case.id = response.data.id;
          console.log(response.data);
          this.submitted = true;
        })
        .catch(e => {
          console.log(e);
        });
    },
    
    newCase() {
      this.submitted = false;
      this.case = {};
    }
  }
};
</script>

<style>
.submit-form {
  max-width: 300px;
  margin: auto;
}
</style>