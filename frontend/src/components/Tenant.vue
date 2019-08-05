<template>
  <b-container class="tenant">
    <b-form-row>
      <b-col><h1>Create tenant</h1></b-col>
    </b-form-row>
    <b-form-row>
      <b-col>
        <b-form-input type="text" v-model="tenant.name" placeholder="name"/>
      </b-col>
      <b-col>
        <b-form-input type="text" v-model="tenant.phone" placeholder="phone"/>
      </b-col>
      <b-col>
        <b-form-select v-model="tenant.room">
          <!-- This slot appears above the options from 'options' prop -->
          <template slot="first">
            <option :value="null" disabled>-- Please select an option --</option>
          </template>
          These options will appear after the ones from 'options' prop
          <option v-for="room in this.roomPage.content" :value="room"
                  :key="room.id">
            {{ room.name }}
          </option>
        </b-form-select>
      </b-col>
      <b-col md="auto">
        <b-button v-if="editTenantFlag === true" variant="success" @click="createtenant()">Update tenant</b-button>
        <b-button v-else variant="success" @click="createtenant()">Create tenant</b-button>
      </b-col>
    </b-form-row>
    <b-form-row>
      <span v-for="item in response">id：{{ item }}</span>
    </b-form-row>
    <b-form-row>
      <b-table hover :items="tenantPage.content" :fields="fields">
        <template slot="id" slot-scope="data">
          <!-- `data.value` is the value after formatted by the Formatter -->
          <a href="javascript:" @click="editTenant(data.value)"><span class="icon-pencil"></span>编辑</a>
          <a href="javascript:" @click="deleteTenant(data.value.id)"><span class="icon-cancel-circle"></span>删除</a>
        </template>
      </b-table>
    </b-form-row>

  </b-container>

</template>

<script>
  // import axios from 'axios'
  import {AXIOS} from './http-common'
  import {mapState} from 'vuex'

  export default {
    name: 'tenant',
    data() {
      return {
        response: [],
        errors: [],
        tenant: {
          id: '',
          name: '',
          phone: ''
        },
        editTenantFlag: false,
        fields: [
          // {
          //   key: "id",
          //   label: 'Id'
          // },
          "name",
          {
            key: 'phone',
            label: '电话号码'
          },
          {
            key: 'id',
            label: '操作',
            formatter: (value, key, item) => {
              return item
            }
          }
        ]
      }
    },
    methods: {
      // Fetches posts when the component is created.
      createtenant() {
        AXIOS.post(`/tenant`, this.tenant)
          .then(response => {
            // JSON responses are automatically parsed.
            this.response.push(response.data);
            this.$store.dispatch('gettenants');
            this.tenant.id = '';
            this.tenant.name = '';
            this.tenant.phone = '';
            this.tenant.room = '';
            this.editTenantFlag = false;
            console.log(response.data)
          })
          .catch(e => {
            this.errors.push(e)
          })
      },
      editTenant (tenant){
        this.tenant = tenant;
        this.editTenantFlag = true
      },
      deleteTenant (tenantid){

        AXIOS.delete(`/tenant`, { params:{'tenantId': tenantid}})
          .then(response => {
            // JSON responses are automatically parsed.
            this.response.push(response.data);
            this.$store.dispatch('getTenants').then(response => {
              console.log("Successfully get tenants")
            });
            this.tenant.name = '';
            this.tenant.phone = '';
            this.tenant.room = '';
          })
          .catch(e => {
            this.errors.push(e)
          })
      }
    },
    computed: mapState({
      // 箭头函数可使代码更简练
      roomPage: state => state.roomPage,   // es6写法，function (state) { return state.count }
      tenantPage: state => state.tenantPage
    }),
    mounted: function(){
      this.$store.dispatch('getTenants').then(response => {
        console.log("Successfully get tenants")
      })
    }
  }

</script>
