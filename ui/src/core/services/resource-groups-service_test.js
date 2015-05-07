'use strict';

describe('Service: ResourceGroups', function () {
  beforeEach(module('ui'));

  var ResourceGroups;
  var $httpBackend;

  beforeEach(inject(function (_ResourceGroups_, _$httpBackend_) {
    ResourceGroups = _ResourceGroups_;
    $httpBackend = _$httpBackend_;
  }));

  it('should GET a list of resource groups', function () {
    $httpBackend.expectGET('/data/resourceGroups.json').respond(
      200,
      [
        {
          "id": 1,
          "DepartmentName": "High School Programs",
          "Acronym": "HSP",
          "DepartmentPrograms": [
            {
              "id": 1,
              "Program": "J-1HS"
            },
            {
              "id": 2,
              "Program": "F-1"
            },
            {
              "id": 3,
              "Program": "STP-IHP"
            },
            {
              "id": 4,
              "Program": "STP-GHP"
            },
            {
              "id": 5,
              "Program": "STP-SSE"
            }
          ],
          "CCI_Departmental_Functions": [
            {
              "id": 1,
              "FunctionName": "Operations",
              "FunctionDescription": "Operation Activities for HSP"
            }
          ],
          "Department_ResourceGroups": [1,2,3,4,6,7,8,9,11,12]
        },
        {
          "id": 2,
          "DepartmentName": "Work Programs",
          "Acronym": "WP",
          "CCI_Departmental_Functions": [
            {
              "id": 2,
              "FunctionName": "Recruitment",
              "FunctionDescription": "Recruitment Activity for WP"
            },
            {
              "id": 3,
              "FunctionName": "Operations",
              "FunctionDescription": "Operations tasks for WP"
            },
            {
              "id": 4,
              "FunctionName": "Services",
              "FunctionDescription": "Service Related activities for WP"
            }
          ]
        },
        {
          "id": 3,
          "DepartmentName": "Greenheart Travel",
          "Acronym": "GHT"
        },
        {
          "id": 4,
          "DepartmentName": "Greenheart Club",
          "Acronym": "GHC"
        },
        {
          "id": 5,
          "DepartmentName": "Greenheart Transforms",
          "Acronym": "GT"
        },
        {
          "id": 6,
          "DepartmentName": "Accounting",
          "Acronym": "Act"
        },
        {
          "id": 7,
          "DepartmentName": "System (Tech)",
          "Acronym": "Sys"
        }
      ]
    );

    var resourceGroups = ResourceGroups.getResourceGroups();

    $httpBackend.flush();

    expect(resourceGroups).to.not.equal(null);
  });

});
